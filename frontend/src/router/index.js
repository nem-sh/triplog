import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import APIError from "../components/error/APIError.vue";
import ArticleWrite from "../views/article/ArticleWrite.vue"
import ArticleSearch from "../views/article/ArticleSearch.vue";
import ArticleInfo from "../views/article/ArticleInfo.vue"

import ArticleList from "../views/article/ArticleList.vue"
import ArticleModify from "../views/article/ArticleModify.vue"
import Like from "../views/likey/Like.vue"

import LikeArticleInfo from "../views/likey/LikeArticleInfo.vue"
import temp from "../views/temporaryArticle/temp.vue"
import PersonalMain from "../views/personal/MainPage.vue"
// import { component } from "vue/types/umd";
Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home
  },
  {
    path: "/like",
    name: "Like",
    component: Like
  },
  {
    path: "/like/detail",
    name: "LikeArticleInfo",
    component: LikeArticleInfo
  },
  {
    path: "/about",
    name: "About",
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    component: () =>
      import(/* webpackChunkName: "about" */ "../views/About.vue")
  },
  {
    path: "/article/ArticleSearch",
    name: "ArticleSearch",
    component: ArticleSearch
  },

  {
    path: "/apierror/:errorCode",
    name: "APIError",
    component: APIError
  },
  {
    path: "/article/write",
    name: "articleWrite",
    component: ArticleWrite
  },
  {
    path: "/article/detail/:articleNum",
    name: "articleDetail",
    component: ArticleInfo
  },
  {
    path: "/article/list/:hostNum",
    name: "articleList",
    component: ArticleList
  },
  {
    path: "/article/modify/:articleNum",
    name: "articleModify",
    component: ArticleModify
  },
  {
    path: "/tempArticle",
    name: "tempArticle",
    component: temp
  },
  {
    path: "/:hostNum",
    name: "PersonalMain",
    component: PersonalMain
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes
});

export default router;
