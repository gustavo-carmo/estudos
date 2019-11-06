import Main from './components/Menu.vue'
import List from './components/MenuList.vue'
import Form from './components/MenuForm.vue'
import Show from './components/MenuShow.vue'

export default [
  {
    path: '/menu', component: Main,
    children: [
      {path: '', component: List, name: 'MenuList'},
      {path: 'create', component: Form, name: 'MenuCreate'},
      {path: 'edit/:id', component: Form, name: 'MenuEdit'},
      {path: 'show/:id', component: Show, name: 'MenuShow'}
    ]
  }
]
