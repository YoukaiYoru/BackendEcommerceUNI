<template>
         <v-card class="ma-5">
            <v-row>
               <v-col cols="10">
            <v-card-title class="w-100">{{ name }}</v-card-title>
            <v-row>
               <v-col cols="3">
                  <v-img :height="100" :width="100" :src="img"></v-img>
               </v-col>
               <v-col cols="5"> 
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
               <v-btn variant="text" icon="mdi-trash-can-outline" @click="deleteProduct" ></v-btn>
            </v-col>
            </v-row>
         </v-card>
</template>

<script setup>

defineProps (['name','price','quantity','img']);
defineEmits(['deleteProduct']);

</script>

<style scoped>
/* Estilos personalizados para la tarjeta */
</style>