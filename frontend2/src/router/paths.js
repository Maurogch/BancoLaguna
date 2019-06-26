/**
 * Define all of your application routes here
 * for more information on routes, see the
 * official documentation https://router.vuejs.org/en/
 */
export default [
  {
    path: '/accounts',
    // Relative to /src/views
    view: 'Accounts'
  },
  {
    path: '/user-profile',
    view: 'UserProfile'
  },
  {
    path: '/account-movements',
    view: 'AccountMovements'
  },
  {
    path: '/icons',
    view: 'Icons'
  },
  {
    path: '/notifications',
    view: 'Notifications'
  },
  {
    path: '/transfer',
    view: 'Transfer'
  }
]
