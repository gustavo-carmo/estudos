import Main from './components/Form.vue'
import List from './components/FormList.vue'
import Form from './components/FormForm.vue'
import Show from './components/FormShow.vue'

export default [
  {
    path: '/form', component: Main,
    children: [
      {path: '', component: List, name: 'FormList'},
      {path: 'create', component: Form, name: 'FormCreate'},
      {path: 'edit/:id', component: Form, name: 'FormEdit'},
      {path: 'show/:id', component: Show, name: 'FormShow'}
    ]
  }
]
