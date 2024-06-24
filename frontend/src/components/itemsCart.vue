<template>
         <v-card class="ma-5">
            <v-row>
               <v-col>
            <v-card-title>{{ name }}</v-card-title>
            <v-row>
               <v-col cols="6">
                  <v-img :src="'https://www.pruebas-edunionline.uni.edu.pe/img/libros/libro-1.png'"></v-img>
               </v-col>
               <v-col> 
                  <p>Precio: {{ price }}</p>
                  <p>Cantidad: {{ quantity }}</p>
               </v-col>
            </v-row>
            <v-col>
               <v-row>

               </v-row>
            </v-col>
         </v-col>
            <v-col cols="2" align="center" justify="center">
               <v-btn variant="text" icon="mdi-trash-can-outline" ></v-btn>
            </v-col>
            </v-row>
         </v-card>
</template>

<script setup>
defineProps (['name','price','quantity','img']);
</script>

<style scoped>
/* Estilos personalizados para la tarjeta */
</style>