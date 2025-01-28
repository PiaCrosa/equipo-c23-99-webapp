import axios from 'axios';
import Swal from 'sweetalert2';
import { InstitutionUpdate } from '../models/admin/InstitutionUpdate'; // Crea este tipo para la estructura
import { getErrorMessage } from '../utils/error';
import { PORT_SERVER } from '.';

export const updateInstitution = async (
	institutionData: InstitutionUpdate,
	token: string | undefined,
): Promise<void> => {
	try {
		await axios.put(`${PORT_SERVER}/institution/update`, institutionData, {
			headers: {
				'Content-Type': 'application/json',
				Authorization: `Bearer ${token}`,
			},
		});

		Swal.fire({
			icon: 'success',
			title: 'Actualización exitosa',
			text: `La institución ${institutionData.name} ha sido actualizada.`,
			timer: 3000,
			showConfirmButton: false,
		});
	} catch (error: unknown) {
		let errorMessage = 'Ocurrió un error desconocido.';

		if (axios.isAxiosError(error) && error.response) {
			errorMessage = getErrorMessage(error.response.status);
		} else if (error instanceof Error) {
			errorMessage = error.message;
		}

		Swal.fire({
			icon: 'error',
			title: 'Error al actualizar',
			text: errorMessage,
		});

		throw new Error(errorMessage);
	}
};
