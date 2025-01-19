import { Route } from './Route'
import { AdminDashboard } from '../pages/AdminDashboard'
import { TeacherDashboard } from '../pages/TeacherDashboard'

const routeList: Route[] = [
  {
    path: '/admin-dashboard',
    name: 'Home',
    routeType: 'admin',
    isShownInMenu: true,
    element: AdminDashboard,
  },
  {
    path: '/teacher-dashboard',
    name: 'Home',
    routeType: 'teacher',
    isShownInMenu: true,
    element: TeacherDashboard,
  },
] as const;

export {
  routeList,
}