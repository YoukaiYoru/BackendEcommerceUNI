
/**
 * router/index.ts
 *
 * Automatic routes for `./src/pages/*.vue`
 */

// Composables
import { createRouter, createWebHistory } from 'vue-router'

const routes = [
  {
    path: '/',
    name: 'home',
    component: ()=> import('@/pages/landingPage.vue')
  },
  {
    path: '/cart',
    name: 'cart',
    component: ()=> import('@/pages/cartPage.vue')
  },
  {
    path: '/productos',
    name: 'productos',
    component: ()=> import('@/pages/productosPage.vue')
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
