Sciprt:
GET v.s. POST
Both of then are HTTP methods. Key difference is that GET is idempotent which means that the same request will always get the same result and POST is not idempotent. 
Another different is that POST sends data through the request body making it more secure and GET retrieves data by sending parameters through the URL.
HTTP have several methods include GET (retrieve data), POST (create new data), PATCH (partail update data), PUT (fully update data), and DELETE (remove data).
