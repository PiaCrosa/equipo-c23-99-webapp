import { useForm } from 'react-hook-form';
import { AddEditUsersForm } from '../../models/AddEditUsersForm';
import { AddEditUsersTitle } from './AddEditUsersTitle';
import { useParams } from 'react-router-dom';

import { AddEditUsersSubmitButton } from './AddEditUsersSubmitButton';
import { useEffect, useState } from 'react';
import { AddEditUsersInputs } from './AddEditUsersInputs';
import { User } from '../../models/User';
import { UserService } from '../../services/UserService';
import {
	showFailAlert,
	showSuccessAlert,
} from '../../helpers/showGenericAlerts';

const AddEditUsers = () => {
	// Initial Hooks
	const { dni } = useParams();
<<<<<<< HEAD
	const {
		register: userRegister,
		handleSubmit: handleUserSubmit,
		reset,
	} =
=======
	const { register: userRegister, handleSubmit: handleUserSubmit } =
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
		useForm<AddEditUsersForm>();
	const [userForm, setUserForm] = useState<AddEditUsersForm>({
		...new AddEditUsersForm(),
	});
<<<<<<< HEAD
	const [userNameToEdit, setUserNameToEdit] = useState('')
=======
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)

	// Services
	const userService = UserService();

	// Functions
	const prepareUserToSubmit = (form: AddEditUsersForm): User => {
		return {
<<<<<<< HEAD
			dni: dni ? dni : '0',
			email: form.email,
			full_name: form.name,
			password: form.pass1,
			password2: form.pass2,
=======
			dni: form.dni,
			email: form.email,
			full_name: form.name,
			institution_cue: form.institution_cue,
			password: form.pass1,
			password2: form.pass2,
			role: form.role,
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
		};
	};

	// Handlers
	const submitUserForm = async (form: AddEditUsersForm) => {
		try {
			const user = prepareUserToSubmit(form);
<<<<<<< HEAD
			if (dni) { await userService.updateUser({ user }); }
			else { await userService.createUser({ user }); }
=======
			await userService.createUser({ user });
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
			showSuccessAlert();
			setUserForm(form);
		} catch (error) {
			console.log(error);
			showFailAlert();
		}
	};

	// Use effects
	useEffect(() => {
<<<<<<< HEAD
		const reFillingForm = async () => {
=======
		const custom = async () => {
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
			const user = await userService.getUserByDni({ dni: dni! });
			setUserForm((prevForm) => {
				const newForm = {
					dni: user.dni,
					email: user.email,
<<<<<<< HEAD
=======
					institution_cue: user.cue || '',
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
					name: user.fullName,
					pass1: '',
					pass2: '',
					role: user.role || 'ADMIN',
				};
				return JSON.stringify(prevForm) !== JSON.stringify(newForm)
					? newForm
					: prevForm;
			});
<<<<<<< HEAD
			setUserNameToEdit(prevName => {
				return String(prevName) !== String(user.fullName)
					? user.fullName
					: prevName;
			})
		};

		if (dni) {
			reFillingForm();
=======
		};

		if (dni) {
			console.log('DNI', dni);
			custom();
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
		} else {
			console.log('No hay dni');
		}
	}, [dni, userService]);

<<<<<<< HEAD
	useEffect(() => {
		if (userForm) {
			reset(userForm);
		}
	}, [userForm, reset]);

=======
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
	return (
		<form
			className='
        p-2 text-sky-500
        sm:px-6 sm:py-4
      '
<<<<<<< HEAD
			onSubmit={handleUserSubmit(submitUserForm)}
		>
			<AddEditUsersTitle userName={userNameToEdit} />
			<AddEditUsersInputs register={userRegister} />
=======
			onSubmit={handleUserSubmit(submitUserForm)}>
			<AddEditUsersTitle userName={dni} />
			<AddEditUsersInputs register={userRegister} usersForm={userForm} />
>>>>>>> d8aaf13 (Merge domenico-datos-institucion con german)
			<AddEditUsersSubmitButton />
		</form>
	);
};

export { AddEditUsers };
