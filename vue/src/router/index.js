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
        const user = JSON.parse(localStorage.getItem('student-user') || '{}')
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
        { path: 'course', name: 'Course', component: () => import('@/views/manager/Course.vue')},
        { path: 'student', name: 'Student', component: () => import('@/views/manager/Student.vue')},
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
