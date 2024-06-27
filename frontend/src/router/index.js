
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
    path: '/login',
    name: 'login',
    component: ()=> import('@/pages/LoginUserPage.vue')
  },
  {
    path: '/register',
    name: 'register',
    component: ()=> import('@/pages/registroPage.vue')
  },
  {
    path: '/productos',
    name: 'productos',
    component: ()=> import('@/pages/productosPage.vue')
  },

  {
    path:'/productos/:id',
    name:'productoDetalle',
    component: ()=> import('@/pages/productoDetallePage.vue')

  },
  {
    path:'/admin',
    name:'admin',
    component: ()=> import('@/pages/admin/landingAdminPage.vue')
  },
  {
    path:'/admin/productos',
    name:'productosAdmin',
    component: ()=> import('@/pages/admin/productosAdminPage.vue')
  },
  {
    path:'/admin/clientes',
    name:'clientesAdmin',
    component: ()=> import('@/pages/admin/clientesAdminPage.vue')
  },
  {
    path:'/admin/ordenes',
    name:'ordenesAdmin',
    component: ()=> import('@/pages/admin/ordenesAdminPage.vue')
  },
]

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes
})

export default router
