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
      > Clientes </v-btn>
      <v-btn 
      size="large"
      class="mx-5"
      > Ordenes </v-btn>
      
      <v-btn
      :ripple="false"
      to="/login"
      class="mx-5"
      variant="plain">
         ¡Hola! <br> Inicia sesión
      </v-btn>
   
      <v-btn icon="mdi-cart-outline"
      size="large"
      class="mx-10"
      to="/cart">
      </v-btn>
   </v-row>
   
   </v-app-bar>
   
   
   </template>
   
   <script setup>

   import { useRouter } from 'vue-router'

   const router = useRouter()
   
   
   const goHome = () => {
      router.push('/admin')
   }
   
   </script>
   
   