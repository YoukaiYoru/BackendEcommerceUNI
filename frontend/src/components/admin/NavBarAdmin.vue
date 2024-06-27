<template>

   <v-app-bar :height="100"
   position-fixed
   class="bg-black"
   >
   <v-row align="center" justify="center">
      <img
      
      @click="goHome()"
      style="object-fit: contain;"
      height="80"
      src="/src/assets/logoMarket.png" class="mr-2 cursor-pointer" alt="Logo"/>
      
   
      <v-btn 
      size="large"
      class="mx-5"
      to="/admin"
      > Inicio </v-btn>
      <v-btn 
      size="large"
      class="mx-5"
      to="/admin/productos"
      > Productos </v-btn>
      <v-btn 
      size="large"
      class="mx-5"
      to="/admin/clientes"
      > Clientes </v-btn>
      <v-btn 
      size="large"
      class="mx-5"
      to="/admin/ordenes"
      > Ordenes </v-btn>
      
      <v-btn
      :ripple="false"
      v-if="!user"
      to="/login"
      class="mx-5"
      variant="plain">
         ¡Hola! <br> Inicia sesión
      </v-btn>
   
      <v-btn icon="mdi-cart-outline"
      v-if="!user"
      size="large"
      class="mx-10"
      to="/cart">
      </v-btn>
      
            <!--Menu from avatar-->
      <v-menu v-if="user" size="32">
         <template v-slot:activator="{props}">
            <v-btn v-bind="props"
            size="large" 
            class="ma-3 mr-5" 
            append-icon="mdi-chevron-down">
               <template v-slot:prepend>
                  <v-avatar>
                     <img src='https://avatars0.githubusercontent.com/u/9064066?v=4&s=460' alt="User" style="display: block; margin: 0 auto; width: 100%; height: 100%;">
                  </v-avatar>
               </template>
            </v-btn>
         </template>

         <v-list class="pa-0 mt-2">
            <v-list-item class="pa-0" align="end">
               <v-btn flat block v-ripple.center  @click="logout">Cerrar Sesión</v-btn>
            </v-list-item>
         </v-list>
      </v-menu>

   </v-row>
   
   </v-app-bar>
   
   
   </template>
   
   <script setup>
   import { ref } from 'vue'
   import { useRouter } from 'vue-router'

   const router = useRouter()
   const user = ref(JSON.parse(localStorage.getItem('user')));
   
   const goHome = () => {
      router.push('/admin')
   }
   const logout = () => {
         localStorage.removeItem('user');
         user.value = null;
         router.push('/');
      };
   
   </script>
   
   