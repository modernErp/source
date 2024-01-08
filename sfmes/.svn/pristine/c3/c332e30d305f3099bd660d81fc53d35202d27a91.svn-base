<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
    <%@ page import ="java.sql.*" %>
    <%@ page import = "java.lang.*" %>
    <%@ page import = "java.util.List" %>
    <%@ page import = "java.util.LinkedHashMap" %>
    <%@ page import = "javax.xml.bind.DatatypeConverter" %>
    <%@ page import = "java.io.UnsupportedEncodingException" %>
<!DOCTYPE html>
<html>
    <head>
        <meta charset="UTF-8">
        <title>Link url Test</title>
</head>

<%!
    ResultSetMetaData rsmd = null;
    String inSql = null;
    Connection conn = null;
    Statement stmt = null;
    String titl = null;
    String sqno = null;
    String redt = null;
    String fileExe = null;
    String filePath = null;
    String chgNm = null;
    String input_query = null;
    
    String colum_NM = null;
    String input_Col = null;
    
    ResultSet rs2 = null;
    
    public ResultSet ListToResultSet(List<?> paramMap){
    	ResultSet lrs = null;   
    	
        paramMap.toArray();
    	
        return lrs;
     }
    


    
%>


<%
    try{
	    
	    
	    String jdbcDriver = "jdbc:mssql://175.123.252.144:9855/sfmes";
	    String jdbcUser = "sfmes_dev";
	    String jdbcPassword = "ahems1!";
	    
	    conn = DriverManager.getConnection("jdbc:sqlserver://175.123.252.144:9855;datebaseName=sfmes","sfmes_dev","ahems1!");
	    
	    stmt = conn.createStatement();
	    

	    
%>

<body>
        <table border = "1">
            <tr><%
                try{
                	input_query = request.getParameter("search");
                	
 //               	System.out.println(input_query);
 //               	System.out.println(hex2Str(input_query));
                	
                	StringBuilder output_query = new StringBuilder();
                	for (int i = 0; i < input_query.length(); i+=2) {
                	  String str = input_query.substring(i, i+2);
                	  output_query.append((char)Integer.parseInt(str, 16));
                	} 
                	
 //               	System.out.println(output_query);
                	
	                rs2 = stmt.executeQuery(output_query.toString());
	                
	                rsmd = rs2.getMetaData();
	                
	                int rsCount = rsmd.getColumnCount();
                
                    for(int i=1; i<=rsCount;i++){
                        colum_NM = rsmd.getColumnName(i);%>
                <th><%=colum_NM%></th><%}%>
            </tr><%
                while(rs2.next()){
                %>
                <tr><%
                    for(int i=1; i<=rsCount;i++){
                        colum_NM = rsmd.getColumnName(i);
                        input_Col = rs2.getString(colum_NM);
                %>
                <th><%=input_Col%></th><%}%>
                </tr><%
                    }
			        }catch(Exception e){
			            e.printStackTrace();
			        }
	        }catch(Exception e){
                e.printStackTrace();
            }finally{
            	rs2.close();
            	stmt.close();
            	conn.close();
            }
				
            %>
        </table>
</body>
</html>