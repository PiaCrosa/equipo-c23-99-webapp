import { Route } from './Route';
import { AdminDashboard } from '../pages/AdminDashboard/AdminDashboard';
import { TeacherDashboard } from '../pages/TeacherDashboard';
import { Users } from '../pages/Users/Users';
import { Inventory } from '../pages/Inventory/Inventory';
import { AddEditUsers } from '../pages/AddEditUsers/AddEditUsers';
import { AddEditDevice } from '../pages/AddEditDevice/AddEditDevice';
import { SearchDevices } from '../pages/SearchDevices/SearchDevices';
import { CheckReservations } from '../pages/CheckReservations/CheckReservations';
import { NewReservation } from '../pages/NewReservation/NewReservation';
import { EditAdmin } from '../pages/EditAdmin/EditAdmin';
import { TermsAndConditions } from '../pages/TermsAndConditions/TermsAndConditions';

const routeList: Route[] = [
	{
		path: '/admin-dashboard',
		name: 'Home',
		routeType: 'ADMIN',
		isShownInMenu: true,
		element: AdminDashboard,
	},
	{
		path: '/edit-admin-profile',
		name: 'Editar Admin',
		routeType: 'ADMIN',
		isShownInMenu: false,
		element: EditAdmin,
	},
	{
		path: '/users',
		name: 'Administrar Usuarios',
		routeType: 'ADMIN',
		isShownInMenu: true,
		element: Users,
	},
	{
		path: '/inventory',
		name: 'Administrar Inventario',
		routeType: 'ADMIN',
		isShownInMenu: true,
		element: Inventory,
	},
	{
		path: '/add-user',
		name: 'Agregar Usuario',
		routeType: 'ADMIN',
		isShownInMenu: false,
		element: AddEditUsers,
	},
	{
		path: '/edit-user/:dni',
		name: 'Editar Usuario',
		routeType: 'ADMIN',
		isShownInMenu: false,
		element: AddEditUsers,
	},
	{
		path: '/add-device',
		name: 'Agregar Dispositivo',
		routeType: 'ADMIN',
		isShownInMenu: false,
		element: AddEditDevice,
	},
	{
		path: '/edit-device/:id',
		name: 'Editar Dispositivo',
		routeType: 'ADMIN',
		isShownInMenu: false,
		element: AddEditDevice,
	},

	{
		path: '/teacher-dashboard',
		name: 'Home',
		routeType: 'TEACHER',
		isShownInMenu: true,
		element: TeacherDashboard,
	},
	{
		path: '/search-device',
		name: 'Ver dispositivos',
		routeType: 'TEACHER',
		isShownInMenu: true,
		element: SearchDevices,
	},
	{
		path: '/check-reservations',
		name: 'Ver mis reservas',
		routeType: 'TEACHER',
		isShownInMenu: true,
		element: CheckReservations,
	},
	{
		path: '/add-reservation',
		name: 'Reservar Dispositivo',
		routeType: 'TEACHER',
		isShownInMenu: false,
		element: NewReservation,
	},
	{
		path: '/conditions',
		name: 'Términos y Condiciones',
		routeType: 'USER',
		isShownInMenu: true,
		element: TermsAndConditions,
	},
] as const;

export { routeList };
