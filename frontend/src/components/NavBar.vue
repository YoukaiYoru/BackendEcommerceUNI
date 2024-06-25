<template>

<v-app-bar :height="100"
position-fixed
>
<v-row align="center" justify="center">
   <img
   style="object-fit: contain;"
   height="80"
   src="/src/assets/logoMarket.png" class="mr-2" alt="Logo"></img>

   <v-btn 
   prepend-icon="mdi-menu"
   size="large"
   class="mx-5"
   > Menu </v-btn>

   <v-text-field
   max-width="500"
   class="mx-5 my-10"
   hide-details="auto"
   v-model="search"
   append-inner-icon="mdi-magnify"
   clearable
   clear-icon="mdi-close-circle-outline"
   variant="solo"
   ></v-text-field>
   
   <v-btn
   :ripple="false"
   class="mx-5"
   variant="plain">
      ¡Hola! <br> Inicia sesión
   </v-btn>

   <v-btn icon="mdi-cart-outline"
   size="large"
   class="mx-10">
   </v-btn>
</v-row>

</v-app-bar>


</template>
