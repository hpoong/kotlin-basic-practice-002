package com.hopoong.security.domain.post

import com.hopoong.security.domain.member.Member
import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.querydsl.from.fetch
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import javax.persistence.criteria.JoinType

@Repository
interface PostRepository: JpaRepository<Post, Long>, PostCustomRepository { }

interface PostCustomRepository {

    fun findAllPosts(): List<Post>
}


class PostCustomRepositoryImpl(
    private val queryFactory : SpringDataQueryFactory
): PostCustomRepository {

    override fun findAllPosts(): List<Post> {
        return queryFactory.listQuery<Post> {
            select(entity(Post::class))
            from(entity(Post::class))
            fetch(Post::member, joinType = JoinType.LEFT)
            orderBy(ExpressionOrderSpec(column(Member::id), false))
        }
    }

}