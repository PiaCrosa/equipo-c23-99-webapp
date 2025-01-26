import { Controller, useForm } from 'react-hook-form'
import { AddEditDeviceForm } from '../../models/AddEditDeviceForm';


const AddEditDevice = () => {
  const {
    register: deviceRegister,
    handleSubmit: handleDeviceSubmit,
    control: deviceControl,
  } = useForm<AddEditDeviceForm>();

  const readDeviceForm = (value: AddEditDeviceForm) => {
    console.log(value);
  }

  const setValueAsBoolean = (value: '0' | '1') => {
    return value === '1' ? true : false;
  }

  return (
    <form
      onSubmit={handleDeviceSubmit(readDeviceForm)}
    >
      <div>
        Editar recurso $Nombre
      </div>
      <div>
        <label>
          Nombre:
        </label>
        <Controller
          control={deviceControl}
          name='category'
          render={({ field: { onChange, onBlur, value } }) => (
            <input
              onChange={onChange}
              onBlur={onBlur}
              value={value}
            />
          )}
        >
          
        </Controller>
      </div>
      <div>
        <label>
          Categoría:
        </label>
        <select
          {...deviceRegister(
            'category',
            { required: true }
          )}
        >
          <option value={1}>1</option>
          <option value={2}>2</option>
          <option value={3}>3</option>
          <option value={4}>4</option>
        </select>
      </div>
      <div>
        <label>
          Descripción:
        </label>
        <textarea
          {...deviceRegister(
            'description',
            { required: true }
          )}
        ></textarea>
      </div>
      <div>
        <label>
          Disponible:
        </label>
        <div>
          <div>
            <label>Si</label>
            <input
              type='radio'
              value={1}
              {...deviceRegister(
                'isAvailable',
                { required: true, setValueAs: setValueAsBoolean }
              )}
            />
          </div>
          <div>
            <label>No</label>
            <input
              type='radio'
              value={0}
              {...deviceRegister(
                'isAvailable',
                { required: true, setValueAs: setValueAsBoolean }
              )}
            />
          </div>
        </div>
      </div>
      <div>
        <input type="submit" value='Enviar' />
      </div>
    </form>
  )
}

export {
  AddEditDevice,
}
