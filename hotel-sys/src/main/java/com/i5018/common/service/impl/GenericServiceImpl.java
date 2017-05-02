package com.i5018.common.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.i5018.common.service.GenericService;

@Transactional
@Service
public class GenericServiceImpl<T> implements GenericService<T> {

}
