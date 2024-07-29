<%--
  Created by IntelliJ IDEA.
  User: dnjs1
  Date: 24. 7. 4.
  Time: 오후 5:29
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://www.springframework.org/security/tags"
           prefix="sec" %>
<html>
<head>
    <meta charset="utf-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0, shrink-to-fit=no">
    <title>파이널</title>
    <link rel="preconnect" href="https://fonts.googleapis.com">
    <link rel="preconnect" href="https://fonts.gstatic.com" crossorigin>
    <link href="https://fonts.googleapis.com/css2?family=Noto+Sans+KR:wght@100..900&display=swap" rel="stylesheet">
    <link rel="stylesheet" href="/assets/bootstrap/css/header.css">
    <link rel="stylesheet" href="/assets/css/header.css">
    <script defer src="https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"></script>
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.7.1/jquery.min.js"></script>
    <script src="https://cdn.jsdelivr.net/npm/sockjs-client@1.5.2/dist/sockjs.min.js"></script>

    <style>
        #header {
            border: 1px solid #e6e7e8;
        }

        * {
            font-family: 'Noto Sans KR';
            font-weight: 500;
        }
    </style>

</head>
<body id="main" class="hd">
<div id="wrap">
    <header id="header" class="hd__header">
        <div class="header">
            <section class="user-area">
                <ul class="user-area__menu">
                    <%--로그인 클릭--%>
                    <sec:authorize access="isAnonymous()">
                        <li><a href="/loginfrm">로그인</a></li>
                        <li><a href="/joinfrm">회원가입</a></li>
                    </sec:authorize>
                    <sec:authorize access="isAuthenticated()">
                        <li>${m_name}님 환영합니다</li>
                        <li><a href="/member/logout">로그아웃</a></li>
                        <sec:authorize access="hasRole('ADMIN')">
                            <li><a href="/main">관리자 페이지</a></li>
                        </sec:authorize>
                    </sec:authorize>
                    <%--회원가입 클릭--%>
                    <%--배송정보 클릭--%>
                    <li><a href="#">배송정보</a></li>
                    <%--고객센터 클릭--%>
                    <li><a href="#">고객센터 </a>
                </ul>
            </section>
            <div class="header__inner">
                <div class="header__sec">
                    <nav class="direct">
                        <div class="direct__search btn__modal-open" data-login="y" id="searchPopup"
                             data-popup-name="popup_search" style="margin-bottom: 10px;">
                            <input type="text"
                                   id="searchInput2"
                                   class="search-input"
                                   placeholder="재료를 입력해 주세요"
                                   autocomplete="off"
                                   style="height: 50px"> <%--추후 input 창 출력 클릭 이벤트 --%>
                            <button type="button" class="direct__search-remove" style="display: none;">지우기</button>
                            <a class="btn__modal-open" data-login="n" data-popup-name="popup_search">검색</a></div>
                        <%--내정보 클릭--%>
                        <a class="direct__btn" href="#"><img
                                src="/assets/img/스크린샷%202024-07-04%20163834.png?h=b11bf208df18b1eb6909c6f72e111c9e"
                                width="69" height="66"></a>

                        <%--레시피 글쓰기 클릭--%>
                        <a href="#"><img
                                src="/assets/img/스크린샷%202024-06-27%20171241.png?h=9f0eff141daebd0b04983e61b2ea4b97"
                                width="72" height="75" style="margin-bottom: -2px;margin-top: -11px;"></a>
                        <a href="https://www.greating.co.kr/order/orderCart" class="direct__cart"> <span
                                id="cartCnt">0</span>
                            <img src="/assets/img/icon_header_cart.png?h=b0cf2eaea34afb39f82041851f5691b1"
                                 alt="장바구니"></a>
                    </nav>
                    <h1 class="logo">
                        <a href="/">
                            <img src="/assets/img/img_header_logo.png" alt=""><%--추후 제목 이미지 넣어야함--%>
                        </a>
                    </h1>
                    <div class="menu">
                        <a class="on" href="/">건강마켓</a><a href="/careMain">식단관리</a>
                    </div>
                </div>
                <div class="gnb">
                    <div class="gnb__inner">
                        <div class="swiper-container">
                            <div class="swiper-wrapper">
                                <%--식자재 링크 제 아니고 재 개킹받네 진짜?--%>
                                <li class="gnb__list"><a id="headCardLink" class="gnb__list-name" href="/fooditem/main">식자재</a>
                                </li>
                                <%--랭킹 링크--%>
                                <li class="gnb__list"><a class="gnb__list-name main_tab" href="#">랭킹</a></li>
                                <%--분류 링크--%>
                                <li class="gnb__list"><a class="gnb__list-name main_tab" href="/recipe/main">분류</a></li>
                                <%--물물교환 링크--%>
                                <li class="gnb__list"><a class="gnb__list-name main_tab" href="/trade/main">물물교환</a>
                                </li>
                            </div>
                        </div>
                    </div>
                </div>
            </div>
        </div>

    </header>
</div>
</body>
</html>
