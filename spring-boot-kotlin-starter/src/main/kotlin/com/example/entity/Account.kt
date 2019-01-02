package com.example.entity

import javax.persistence.*

@Entity
@Table(name = "accounts")
data class Account(
        @Id
        @GeneratedValue(strategy = GenerationType.AUTO)
        var id: Long? = null,
        var name: String? = null
) {
    @OneToMany(cascade = [(CascadeType.ALL)], fetch = FetchType.LAZY, orphanRemoval = true)
    @JoinColumn(name = "account_id")
    var articles: MutableList<Article> = mutableListOf()

    fun addArticle(article: Article) {
        this.articles.add(article)
    }
}
