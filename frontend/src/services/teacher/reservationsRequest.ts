import axios from 'axios';
import Swal from 'sweetalert2';
import { PORT_SERVER } from '..';

export const getReservations = async (token: string): Promise<null> => {
	console.log(axios, Swal, PORT_SERVER, token);
	return null;
};
