// logica para la iniciar sesion
import axios from 'axios';
import { LoginResponse, UserCredentials } from '../context/user';

const loginRequest = async (
	loginData: UserCredentials,
): Promise<LoginResponse | null> => {
	try {
		const response = await axios.post<LoginResponse>(
			'http://localhost:8080/login',
			loginData,
		);
		return response.data;
	} catch (error) {
		console.error('Error al iniciar sesión:', error);
		return null;
	}
};

export default loginRequest;
