import { createRouter, createWebHistory, RouteRecordRaw } from 'vue-router'
import HomeView from '../views/HomeView.vue'
import LoginView from '../views/AuthenticationView.vue'
import DoctorsView from '../views/DoctorsView.vue'
import PatientsView from '../views/PatientsView.vue'
import AppointmentsView from '../views/AppointmentsView.vue'
import FormView from '../views/FormView.vue'

const routes: Array<RouteRecordRaw> = [
  {
    path: '/',
    name: 'home',
    component: HomeView
  },
  {
    path: '/login',
    name: 'login',
    component: LoginView
  },
  {
    path: '/doctors',
    name: 'doctors',
    component: DoctorsView
  },
  {
    path: '/patients',
    name: 'patients',
    component: PatientsView
  },
  {
    path: '/appointments',
    name: 'appointments',
    component: AppointmentsView
  },
  {
    path: '/form/:type/:id',
    name: 'form',
    props: true,
    component: FormView
  },
  /*
  {
    path: '/about',
    name: 'about',
    // route level code-splitting
    // this generates a separate chunk (about.[hash].js) for this route
    // which is lazy-loaded when the route is visited.
    // component: () => import(/ * webpackChunkName: "about" * / '../views/AboutView.vue')
  }
  */
]

const router = createRouter({
  history: createWebHistory(process.env.BASE_URL),
  routes
})

export default router
