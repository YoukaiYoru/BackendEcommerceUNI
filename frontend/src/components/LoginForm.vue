<template>
  <v-sheet class="ma-auto">
    <v-container>
      <v-row>
        <v-col cols="12" md="6" class="mx-auto">
          <v-card>
            <v-card-title class="text-center">Iniciar Sesión</v-card-title>
            <v-card-text>
              <v-form>
                <v-text-field
                  v-model="username"
                  label="Usuario"
                  outlined
                  required
                ></v-text-field>
                <v-text-field
                  v-model="password"
                  label="Contraseña"
                  outlined
                  required
                  type="password"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-btn @click="handleSubmit" color="primary">Iniciar Sesión</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-sheet>
  </template>
  
  <script setup>
  import { ref } from 'vue';
  import { useRouter } from 'vue-router';

  const username = ref('');
  const password = ref('');

  const router = useRouter();
  
  const handleSubmit = () => {
    if (username.value === 'admin') {
      router.push('/admin');
      localStorage.setItem('user', JSON.stringify(username.value));
      console.log(`Usuario: ${username.value}, Contraseña: ${password.value}`);
    }
  };
  </script>
  
  <style scoped>
  
  </style>
  