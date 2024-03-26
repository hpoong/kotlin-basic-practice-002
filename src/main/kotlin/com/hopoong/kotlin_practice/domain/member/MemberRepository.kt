package com.hopoong.kotlin_practice.domain.member

import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils

@Repository
interface MemberRepository: JpaRepository<Member, Long>, MemberCustomRepository { }


interface MemberCustomRepository{
    fun findMembers(pageable: Pageable): Page<Member>
}


class MemberCustomRepositoryImpl(
    private val queryFactory : SpringDataQueryFactory
): MemberCustomRepository {

    /*
     * 사용자 조회
     */
    override fun findMembers(pageable: Pageable): Page<Member> {

        val content = queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
            limit(pageable.pageSize)
            offset(pageable.offset.toInt())
            orderBy(ExpressionOrderSpec(column(Member::id), false))
        }

        val countQuery = queryFactory.listQuery<Member> {
            select(entity(Member::class))
            from(entity(Member::class))
        }

        return PageableExecutionUtils.getPage(content, pageable) {
            countQuery.size.toLong()
        }
    }
}



