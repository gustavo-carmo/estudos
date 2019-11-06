import Main from './components/Systems.vue'
import List from './components/SystemsList.vue'

export default [
    {
        path: '/systems', component: Main,
        children: [
            {path: '', component: List, name: 'SystemsList'}
        ]
    }
]
