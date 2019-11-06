import Main from './components/BusinessLine.vue'
import List from './components/BusinessLineList.vue'
import Form from './components/BusinessLineForm.vue'
import Background from './components/BusinessLineFormBackground.vue'
import Show from './components/BusinessLineShow.vue'

export default [
  {
    path: '/business-line', component: Main,
    children: [
      {path: '', component: List, name: 'BusinessLineList'},
      {path: 'create', component: Form, name: 'BusinessLineCreate'},
      {path: 'edit/:id', component: Form, name: 'BusinessLineEdit'},
      {path: 'show/:id', component: Show, name: 'BusinessLineShow'},
      {path: 'background/:id', component: Background, name: 'BusinessLineFormBackground'}
    ]
  }
]
