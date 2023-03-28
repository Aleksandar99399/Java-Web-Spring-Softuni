package com.softuni.web;

import org.springframework.web.bind.annotation.RequestMapping;

@RequestMapping(AuthorNameSpace.URI_AUTHORS)
public interface AuthorNameSpace {
    String URI_AUTHORS = "/authors";
}
