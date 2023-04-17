import Vue from "vue";
import VueRouter from "vue-router";
import Home from "../views/Home.vue";
import Tickets from "../views/Tickets";
import Customers from "../views/Customers";
import Users from "../views/Users";
import Settings from "../views/Settings";

Vue.use(VueRouter);

const routes = [
  {
    path: "/",
    name: "Home",
    component: Home,
  },
  {
    path: "/tickets",
    name: "Tickets",
    component: Tickets,
  },
  {
    path: "/customers",
    name: "Customers",
    component: Customers,
  },
  {
    path: "/users",
    name: "Users",
    component: Users,
  },
  {
    path: "/settings",
    name: "Settings",
    component: Settings,
  },
];

const router = new VueRouter({
  mode: "history",
  base: process.env.BASE_URL,
  routes,
});

export default router;
