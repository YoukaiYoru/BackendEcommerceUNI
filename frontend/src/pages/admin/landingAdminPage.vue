<template>
   <v-container>
      <v-sheet>
         <v-row class="d-flex justify-center">
            <v-card-title align="center">
               Bienvenido a la página de administrador
               <p class="text-h4">{{ usuario }}</p>
            </v-card-title>
         </v-row>
         <v-row >
            <v-col align="center">
               <v-card @click="$router.push('/admin/productos')" class="bg-red-lighten-5" height="320">
                  <v-card-title>PRODUCTOS</v-card-title>
                  <v-img :src="'/src/assets/admin/shop.svg'" class="w-25 h-25" ></v-img>
                  <v-divider class="ma-5"></v-divider>
                  <v-card-text>Adiciona nuevos productos y modifica el stock de productos ya existentes</v-card-text>
                  <v-divider class="my-5 mx-5"></v-divider>
               </v-card>
            </v-col>
            <v-col align="center">
               <v-card @click="$router.push('/admin/clientes')" class="bg-red-lighten-5" height="320">
                  <v-card-title>CLIENTES</v-card-title>
                  <v-img :src="'/src/assets/admin/clientes.svg'" class="w-25 h-25"></v-img>
                  <v-divider class="ma-5"></v-divider>
                  <v-card-text>Búsqueda de clientes respecto a su usuario e ID para visualizar detalles más específicos</v-card-text>
                  <v-divider class="my-5 mx-5"></v-divider>
               </v-card>
            </v-col>
            <v-col align="center">
               <v-card @click="$router.push('/admin/ordenes')" class="bg-red-lighten-5" height="320">
                  <v-card-title>ORDENES</v-card-title>
                  <v-img :src="'/src/assets/admin/productos.svg'" class="w-25 h-25"></v-img>
                  <v-divider class="ma-5"></v-divider>
                  <v-card-text>Especificaciones de las ordenes realizadas obteniendo diferentes datos previamente brindados</v-card-text>
                  <v-divider class="my-5 mx-5"></v-divider>
               </v-card>
            </v-col>
         </v-row>
      </v-sheet>
   </v-container>
</template>

<script setup>
import {ref,onMounted} from 'vue';

const usuario = ref('');

onMounted(() => {
   usuario.value = localStorage.getItem('user');
})

</script>