# Chat Server

## The following curl commands have been added - make sure to use the correct port and war file for your system

### Clear messages (range) - specify a start time, end time
curl localhost:8080/ChatServer_war/servlet.ClearServlet --data-urlencode "dateFrom=2020-10-11T13:54:30" --data-urlencode "dateTo=2020-10-11T14:33:30"  --referer http://localhost:8080 -v -L

### Clear messages (all) - "clear all" messages
curl localhost:8080/ChatServer_war/servlet.ClearServlet --data-urlencode "dateFrom=2020-10-11T13:54:30" --data-urlencode "dateTo=2020-10-11T14:33:30" --data-urlencode "clearAll=clear" --referer http://localhost:8080 -v -L

### Post a message - specify username and message
curl localhost:8080/ChatServer_war/servlet.BasicServlet --data-urlencode "userName=<strong>Encoded name</strong>" --data-urlencode "message=<i>some message</i>" --referer http://localhost:8080 -v -L

### Download messages in XML
curl localhost:8080/ChatServer_war/servlet.BasicServlet --data-urlencode "startTime=2020-10-11T13:30:30" --data-urlencode "endTime=2020-10-11T13:40:30" --data-urlencode "format=xml" --referer http://localhost:8080 -v -G --output chat_messages.xml

### Download messages in TXT
curl localhost:8080/ChatServer_war/servlet.BasicServlet --data-urlencode "startTime=2020-10-11T13:30:30" --data-urlencode "endTime=2020-10-11T13:40:30" --referer http://localhost:8080 -v -G --output chat_messages.txt

Parameters:
- `--data-urlencode` - each query parameter is separately encoded to ensure consistency.
- `--referer` - security requirement. Any request without the `referer` header must be reject by our endpoints.
- `-L` - command will redirect to any location the servlet is redirecting (Requirement).
- `-v` - full responsive view (Requirement).
- `-G` - execute GET request.
- `--output` - output file for the download.