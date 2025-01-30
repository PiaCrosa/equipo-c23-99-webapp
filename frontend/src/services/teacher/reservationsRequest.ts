import axios from 'axios';
import Swal from 'sweetalert2';
import { PORT_SERVER } from '..';
import { ReservationResponse } from '../../models/teacher/ReservationGetUser';

export const getReservationsByUser = async (
	dni: string,
	page: number = 0,
): Promise<ReservationResponse | null> => {
	try {
		const response = await axios.get<ReservationResponse>(
			`${PORT_SERVER}/${dni}`,
			{
				params: { page },
			},
		);

		return response.data;
	} catch (error) {
		console.error('Error al obtener las reservas:', error);
		Swal.fire({
			title: 'Error',
			text: 'No se pudieron obtener las reservas. Intenta nuevamente.',
			icon: 'error',
			confirmButtonText: 'Aceptar',
		});
		return null;
	}
};
