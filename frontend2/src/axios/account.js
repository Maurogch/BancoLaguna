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
  // Transfer money from one account to another
  transfer: (id, cbu, sum) => instance.post('account/'+id+'/transfer', {id: id, destinationCbu: cbu, sum: sum})  
  // (D)elete  
  //removeForId: (id) => instance.delete('todos/'+id)  
}