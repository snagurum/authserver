# authserver


## testing using custom userdetails login:

curl -i -X POST -d "username=user@example.com&password=password" -c auth_cookies.txt http://localhost:8080/login
curl -i -b auth_cookies.txt http://localhost:8080/welcome

## testing cors error:
http://localhost:8080/help




