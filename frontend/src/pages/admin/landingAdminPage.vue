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
               <v-card>
                  <v-card-title>PRODUCTOS</v-card-title>
                  <v-icon style="height:400px ; content: fill;" icon="mdi-shopping-outline"></v-icon>
                  <v-divider class="ma-5"></v-divider>
                  <v-card-text>Adiciona nuevos productos y modifica el stock de productos ya existentes</v-card-text>
                  <v-divider class="my-5 mx-5"></v-divider>
               </v-card>
            </v-col>
            <v-col align="center">
               <v-card>
                  <v-card-title>CLIENTES</v-card-title>
                  <v-icon style="height:400px ">
                     mdi-account-multiple-outline
                  </v-icon>
                  <v-divider class="ma-5"></v-divider>
                  <v-card-text>Adiciona nuevos productos y modifica el stock de productos ya existentes</v-card-text>
                  <v-divider class="my-5 mx-5"></v-divider>
               </v-card>
            </v-col>
            <v-col align="center">
               <v-card>
                  <v-card-title>ORDENES</v-card-title>
                  <v-icon style="height:400px ; content: fill;" icon="mdi-package-variant-closed"></v-icon>
                  <v-divider class="ma-5"></v-divider>
                  <v-card-text>Adiciona nuevos productos y modifica el stock de productos ya existentes</v-card-text>
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