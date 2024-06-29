<template>
  <v-sheet class="ma-auto">
    <v-container>
      <v-row>
        <v-col cols="12" md="6" class="mx-auto">
          <v-card class="ma-5">
            <v-card-title class="text-center">Iniciar Sesión</v-card-title>
            <v-card-text>
              <v-form>
                <v-card-text class="font-weight-bold">Usuario</v-card-text>
                <v-text-field
                  variant="solo"
                  v-model="username"
                  required
                  :error-messages="errorMessages"
                  :rules="usernameRules"
                ></v-text-field>
                <v-card-text class="font-weight-bold">Contraseña</v-card-text>
                <v-text-field
                  variant="solo"
                  v-model="password"
                  required
                  type="password"
                  :rules="passwordRules"
                ></v-text-field>
              </v-form>
            </v-card-text>
            <v-card-actions>
              <v-btn @click="handleSubmit" class="bg-red-darken-4">Iniciar Sesión</v-btn>
              <v-card-text align="end">No tienes cuenta? </v-card-text>
              <v-btn @click="router.push('/register')" class="bg-red-darken-4">Registrate</v-btn>
            </v-card-actions>
          </v-card>
        </v-col>
      </v-row>
    </v-container>
  </v-sheet>
  </template>
  
  <script setup>
  import { ref, watch } from 'vue';
  import { useRouter } from 'vue-router';
  import { useAuthStore } from '@/stores/authStore';
  import { errorMessages } from 'vue/compiler-sfc';

  const username = ref('');
  const password = ref('');


const router = useRouter();

const authStore = useAuthStore();

const handleSubmit = async () => {
  try {
    // Llama a la acción de login del store de autenticación
    await authStore.login({ username: username.value, password: password.value });

    // Verifica si el usuario está autenticado correctamente
    const storedUser = localStorage.getItem('user');
    if (storedUser) {
      const parsedUser = JSON.parse(storedUser);
      if (parsedUser && parsedUser.username === username.value) {
        // Redirige según el tipo de usuario
        if (parsedUser.username === 'admin') {
          router.push('/admin'); // Redirige a la página de administrador
        } else {
          router.push('/'); // Redirige a la página principal u otra página
        }
      } else {
        // Si no se estableció correctamente el usuario en localStorage
        throw new Error('Error en el inicio de sesión');
      }
    } else {
      throw new Error('Usuario no encontrado en localStorage');
    }
  } catch (error) {
    console.error('Error al iniciar sesión:', error); // Mensaje de error al usuario
  }
};

const usernameRules = [
  (v) => !!v || 'Usuario requerido',
];


const passwordRules = [
  (v) => !!v || 'Contraseña requerida',   
];


watch(name, () => {
  errorMessages.value = '';
})
  </script>
  
  <style scoped>
  
  </style>
  