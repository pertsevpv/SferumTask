package com.example.exceptions

class NoMoneyException(req: Long, got: Long) : BookShopException("User need $req money to but this product, but has only $got")