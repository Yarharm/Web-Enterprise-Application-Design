# Web-Enterprise-Application-Design
Set of practical resources for the Web-Based Enterprise Application Design

## The following curl commands have been added - make sure to use the correct port and war file for your system

### Clear messages - specify a start time, end time, and whether "clear all" is selected
curl localhost:9999/soen387new_war/servlet.ClearServlet -d "dateFrom=2020-01-01T01:01&dateTo=2020-12-12T01:01&clearAll=False" --location

### Post a message - specify username and message
curl localhost:9999/soen387new_war/servlet.BasicServlet -d "userName=CurlUser&message=curlmessage" --location

