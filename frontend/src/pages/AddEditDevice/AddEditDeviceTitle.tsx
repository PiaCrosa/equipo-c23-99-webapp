interface AddEditDeviceTitleProps {
  deviceName: string | undefined,
}
const AddEditDeviceTitle = ({ deviceName }: AddEditDeviceTitleProps) => {

  return (
    <div className='
      text-2xl pb-3
      md:pb-8
    '>
      {
        deviceName
        ? `Editar ${deviceName}`
        : 'Agregar nuevo recurso'
      }
    </div>
  )
}

export {
  AddEditDeviceTitle,
}