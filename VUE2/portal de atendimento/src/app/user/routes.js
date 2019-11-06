import Main from './components/User.vue'
import Form from './components/UserForm.vue'
import List from './components/UserList.vue'
import Show from './components/UserShow.vue'

export default [
  {
    path: '/user', component: Main,
    children: [
      {path:'', component: List, name: 'UserList', meta: {role: 'ROLE_USER'}},
      {path:'create', component: Form, name: 'UserCreate', meta: {role: 'ROLE_USER'}},
      {path:'edit/:id', component: Form, name: 'UserEdit', meta: {role: 'ROLE_USER'}},
      {path:'show/:id', component: Show, name: 'UserShow', meta: {role: 'ROLE_USER'}}
    ]
  }
]
