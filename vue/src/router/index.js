import {createRouter, createWebHistory} from 'vue-router'

const router = createRouter({
  history: createWebHistory(import.meta.env.BASE_URL),
  routes: [
    {
      path: '/',
      name: 'Manager',
      component: () => import('@/views/Manager.vue'),
      redirect: '/home',
      beforeEnter: (to, from, next) => {
        const user = JSON.parse(localStorage.getItem('login-user') || '{}')
        if (!user || !user.username) {
          // User is not logged in, redirect to login page or other appropriate route
          next('/login') // Replace '/login' with the route you want to redirect to
        } else {
          // User is logged in, proceed to the requested route
          next()
        }
      },
      children: [
        { path: 'home', name: 'Home', component: () => import('@/views/manager/Home.vue')},
        { path: 'exercise', name: 'Exercise', component: () => import('@/views/manager/Exercise.vue')},
        { path: 'user', name: 'User', component: () => import('@/views/manager/User.vue')},
        { path: 'profile', name: 'Profile', component: () => import('@/views/manager/Profile.vue')},
        { path: 'exerciseList', name: 'ExerciseList', component: () => import('@/views/manager/ExerciseList.vue')},
        { path: 'userSelected', name: 'UserSelected', component: () => import('@/views/manager/UseSelected.vue')},
      ]
    },
    {
      path: '/login',
      name: 'Login',
      component: () => import('@/views/Login.vue'),
    },
    {
      path: '/signup',
      name: 'Signup',
      component: () => import('@/views/Signup.vue'),
    }
  ]
})

export default router
