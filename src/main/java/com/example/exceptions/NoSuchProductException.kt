package com.example.exceptions

class NoSuchProductException(req: Long, got: Long) : BookShopException("User require $req, but there are only $got")