import Main from './components/Contract.vue'
import List from './components/ContractList.vue'
import Form from './components/ContractForm.vue'
import Show from './components/ContractShow.vue'
import ContractBackgroundForm from './components/ContractBackgroundForm.vue'

export default [
  {
    path: '/contract', component: Main,
    children: [
      {path: '', component: List, name: 'ContractList'},
      {path: 'create', component: Form, name: 'ContractCreate'},
      {path: 'edit/:id', component: Form, name: 'ContractEdit'},
      {path: 'show/:id', component: Show, name: 'ContractShow'},
      {path: 'image/:type/:id', component: ContractBackgroundForm, name: 'ContractBackgroundForm'}
    ]
  }
]
