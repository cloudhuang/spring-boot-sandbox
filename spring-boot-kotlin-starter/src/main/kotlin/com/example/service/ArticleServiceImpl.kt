package com.example.service

import com.example.entity.Article
import com.example.repository.ArticleRepository
import com.example.api.ArticleService
import com.example.base.BaseServiceImpl
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Service
@Transactional
class ArticleServiceImpl(articleRepository: ArticleRepository) : BaseServiceImpl<Article, Long>(articleRepository), ArticleService {

}