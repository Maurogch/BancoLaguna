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
  getAll: () => instance.get('client'),
  getAllTransfersByClientId: (id) => instance.get('client/'+id+'/transfers'),//,  
  // (U)pdate  
  //updateForId: (id, text, completed) => instance.put('todos/'+id, {title: text, completed: completed}),  
  // (D)elete  
  //removeForId: (id) => instance.delete('todos/'+id)  
}