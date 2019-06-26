import axios from 'axios'  
const SERVER_URL = 'http://localhost:8080';  
const instance = axios.create({  
  baseURL: SERVER_URL,  
  timeout: 1000  
});

export default {  
  // (C)reate  
  //createNew: (text, completed) => instance.post('todos', {title: text, completed: completed}),  
  // (R)ead  
  getAll: () => instance.get('account'),
  // (U)pdate  
  updateForId: (id, newBalance) => instance.put('account/'+id, {balance: newBalance}),  
  // (D)elete  
  //removeForId: (id) => instance.delete('todos/'+id)  
}