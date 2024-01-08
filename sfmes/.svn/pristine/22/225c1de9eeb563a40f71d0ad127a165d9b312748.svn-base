package egovframework.com;

import java.io.IOException;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import java.lang.reflect.Proxy;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.apache.ibatis.session.SqlSessionFactory;
import org.mybatis.spring.SqlSessionFactoryBean;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.core.io.Resource;

/**------------------------------------------
 * mybatis 
 * WAS 재기동 없이 QUERY RELOAD
 * 20211209 YDH
 --------------------------------------------*/
public class RefreshableSqlSessionFactoryBean extends SqlSessionFactoryBean implements DisposableBean {

        private static final Log log = LogFactory.getLog(RefreshableSqlSessionFactoryBean.class);
        
        private SqlSessionFactory proxy;
        private int interval = 1000;
        
        private Timer timer;
        private TimerTask task;
        
        private Resource[] mapperLocations;
        
        /**
         * 파일 감시 쓰레드가 실행중인지 여부.
         */
        private boolean running = false;
        
        private final ReentrantReadWriteLock rwl = new ReentrantReadWriteLock();
        private final Lock r = rwl.readLock();
        private final Lock w = rwl.writeLock();
        
        @Override
        public void setMapperLocations(Resource[] mapperLocations) {
            super.setMapperLocations(mapperLocations);
            this.mapperLocations = mapperLocations;
        }
        
        public void setInterval(int interval) {
            this.interval = interval;
        }
        
        /**
         * @throw Exception
         */
        public void refresh() throws Exception {

            w.lock();
            try {
                super.afterPropertiesSet();
                
            } finally {
                w.unlock();
            }
            
            log.info("sqlMapClient Refreshed.");
        }
        
        /**
         * 싱글톤 멤버로 SqlMapClient 원본 대시 프록시로 설정하도록 오버라이드.
         */
        @Override
        public void afterPropertiesSet() throws Exception {
            super.afterPropertiesSet();
            
            setRefreshable();
        }
        
        private void setRefreshable() {
            proxy = (SqlSessionFactory) Proxy.newProxyInstance(SqlSessionFactory.class.getClassLoader(),
                    new Class[]{SqlSessionFactory.class},
                    new InvocationHandler() {
                @Override
                public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
                    return method.invoke(getParentObject(), args);
                }
            });
            
        task = new TimerTask() {
            private Map<Resource, Long> map = new HashMap<Resource, Long>();
            
            @Override
            public void run() {
                if(isModified()) {
                    try{
                        refresh();
                    } catch (Exception e) {
                        log.error("caught exception", e);
                    }
                }
            }
            
            private boolean isModified() {
                boolean retVal = false;
                
                if(mapperLocations != null) {
                    for(int i = 0; i < mapperLocations.length; i++) {
                        Resource mappingLocation = mapperLocations[i];
                        retVal = findModifiedResource(mappingLocation);
                        if(retVal) {
                            //변경된 파일을 찾았으면 반복문 빠져나옴..
                            break;  
                        }
                    }
                }
            
                return retVal;
            }
            
            private boolean findModifiedResource(Resource resource) {
                boolean retVal = false;
                List<String> modifiedResources = new ArrayList<String>();
                
                try {
                    
                    long modified = resource.lastModified();
                    
                    if(map.containsKey(resource)) {
                        long lashModified = map.get(resource).longValue();
                        
                        if(lashModified != modified) {
                            map.put(resource, new Long(modified));
                            modifiedResources.add(resource.getDescription());
                            retVal = true;
                        }
                    } else {
                        map.put(resource, new Long(modified));
                    }
                } catch (IOException e) {
                    log.error("caught exception", e);                
                }
                if(retVal) {
                    if(log.isInfoEnabled()) {
                        log.info("modified files : "+ modifiedResources);
                    }
                }
                return retVal;
            }
        };
        
        timer = new Timer(true);
        resetInterval();
        
    }
    
    private Object getParentObject() throws Exception {
        r.lock();
        try {
            return super.getObject();
        } finally {
            r.unlock();
        }
    }
    
    @Override
    public SqlSessionFactory getObject() {
        return this.proxy;
    }
    
    @Override
    public Class<? extends SqlSessionFactory> getObjectType() {
        return (this.proxy != null ? this.proxy.getClass() : SqlSessionFactory.class);
    }
    
    @Override
    public boolean isSingleton() {
        return true;
    }
    
    public void setCheckInterval(int ms) {
        interval = ms;
        
        if(timer != null) {
            resetInterval();
        }
    }
    
    public void resetInterval() {
        if(running) {
            timer.cancel();
            running = false;
        }
        if(interval > 0) {
            timer.schedule(task, 0, interval);
            running = true;        
        }
    }
    
    @Override
    public void destroy() throws Exception {
        timer.cancel();
    }
}