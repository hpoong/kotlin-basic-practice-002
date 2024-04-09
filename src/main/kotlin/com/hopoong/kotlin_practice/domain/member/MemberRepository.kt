package com.hopoong.kotlin_practice.domain.member

import com.hopoong.kotlin_practice.exception.BusinessException
import com.hopoong.kotlin_practice.response.ErrorCodeEnum
import com.linecorp.kotlinjdsl.query.spec.ExpressionOrderSpec
import com.linecorp.kotlinjdsl.querydsl.expression.col
import com.linecorp.kotlinjdsl.querydsl.expression.column
import com.linecorp.kotlinjdsl.spring.data.SpringDataQueryFactory
import com.linecorp.kotlinjdsl.spring.data.listQuery
import com.linecorp.kotlinjdsl.spring.data.selectQuery
import com.linecorp.kotlinjdsl.spring.data.singleQuery
import org.springframework.data.domain.Page
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import org.springframework.data.domain.Pageable
import org.springframework.data.support.PageableExecutionUtils
import javax.persistence.NoResultException
import javax.persistence.TypedQuery

@Repository
interface MemberRepository: JpaRepository<Member, Long>, MemberCustomRepository { }


interface MemberCustomRepository{
    fun findMembers(pageable: Pageable): Page<Member>
    fun findMembers1(username: String): Member?
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

//    override fun findMembers1(username: String): Member {
//        val parentComment: Member? =
//            try {
//                queryFactory.singleQuery<Member> {
//                    select(entity(Member::class))
//                    from(entity(Member::class))
//                    where(
//                        col(Member::email).equal(username)
//                    )
//                }
//            } catch (ex: NoResultException) {
//                null
//            }
//
//         if(parentComment != null)
//             return parentComment
//        else
//            throw throw BusinessException(ErrorCodeEnum.ERROR_MEMBER_SEARCH)
//    }


    override fun findMembers1(username: String): Member? {
        return try {
            queryFactory.singleQuery<Member> {
                select(entity(Member::class))
                from(entity(Member::class))
                where(col(Member::email).equal(username))
            }
        } catch (ex: NoResultException) {
            return null
        }
    }

}



