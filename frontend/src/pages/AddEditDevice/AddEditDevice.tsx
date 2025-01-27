import { useForm } from 'react-hook-form';
import { AddEditDeviceForm } from '../../models/AddEditDeviceForm';
import { AddEditDeviceTitle } from './AddEditDeviceTitle';
import { useParams } from 'react-router-dom';

import { AddEditDeviceSubmitButton } from './AddEditDeviceSubmitButton';
import { useState } from 'react';
import { Resource } from '../../models/Resource';
import { AddEditDeviceInputs } from './AddEditDeviceInputs';
import { ResourceService } from '../../services/ResourceService';

const initialDeviceFormValue: AddEditDeviceForm = {
	id: 0,
	category: 'IT',
	description: 'lorem',
	name: 'Gemran',
	isAvailable: 'true',
};

const AddEditDevice = () => {
	// Initial Hooks
	const { id } = useParams();
	const { register: deviceRegister, handleSubmit: handleDeviceSubmit } =
		useForm<AddEditDeviceForm>();
	const [deviceForm, setDeviceForm] = useState<AddEditDeviceForm>({
		...initialDeviceFormValue,
	});
	const resourceServices = ResourceService();

	// Handlers

	// useEFfect

	// Functions
	const updateLocalDeviceForm = (formValue: AddEditDeviceForm) => {
		setDeviceForm({ ...formValue });
	};
	const prepareResourceToSubmit = (): Resource => {
		return {
			inventoryId: deviceForm.id,
			category: deviceForm.category,
			description: deviceForm.description,
			name: deviceForm.name,
			status: deviceForm.isAvailable == 'true' ? 'AVAILABLE' : 'UNAVAILABLE',
		};
	};
	const submitDeviceFormToServer = async (resource: Resource) => {
		await resourceServices.createResource({ resource });
	};

	// Handlers
	const submitDeviceForm = (value: AddEditDeviceForm) => {
		updateLocalDeviceForm(value);
		const resource = prepareResourceToSubmit();
		submitDeviceFormToServer(resource);
	};

	return (
		<form
			className='
        p-2 text-sky-500
        sm:px-6 sm:py-4
      '
			onSubmit={handleDeviceSubmit(submitDeviceForm)}>
			<AddEditDeviceTitle deviceName={id} />
			<AddEditDeviceInputs register={deviceRegister} deviceForm={deviceForm} />
			<AddEditDeviceSubmitButton />
		</form>
	);
};

export { AddEditDevice };
