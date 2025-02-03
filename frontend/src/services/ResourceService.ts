import { useState } from 'react';
import { PORT_SERVER } from '.';
import { useGetCurrentUser } from '../helpers/hooks/useGetCurrentUser';
import { LoginResponse } from '../context/user';
import axios from 'axios';
import { headersWithToken } from '../helpers/headersWithToken';
import { Device } from '../models/Device';


const DeviceService = () => {
	// Custom Hooks
	const getCurrentUser = useGetCurrentUser;

	// States
	const [currentUser, setCurrentUser] = useState<LoginResponse | null>(null);

	// Handlers
	const handleUser = (user: LoginResponse | null) => {
		if (currentUser?.jwtToken !== user?.jwtToken) {
			setCurrentUser(user ? user : null);
		}
	};

	// Effects
	getCurrentUser({ onUpdateUser: handleUser });

	return {
		getDeviceById: async (id: number) => {
			const url = `${PORT_SERVER}/resource/${id}`;
			if (!currentUser) throw new Error('Usuario no logueado');
			const config = headersWithToken(currentUser.jwtToken);
			const response = await axios.get(url, config);
      return response.data.data;
		},
		createDevice: async (device: Device) => {
			const url = `${PORT_SERVER}/resource/add-resource`;
			const data = { ...device };
			if (!currentUser) throw new Error('Usuario no logueado');
			const config = headersWithToken(currentUser.jwtToken);
			await axios.post(url, data, config);
		},
		updateDevice: async (device: Device) => {
			const url = `${PORT_SERVER}/resource/${device.inventoryId}`;
			const data = { ...device };
			if (!currentUser) throw new Error('Usuario no logueado');
			const config = headersWithToken(currentUser.jwtToken);
			await axios.put(url, data, config);
		},
	};
};

export { DeviceService };
