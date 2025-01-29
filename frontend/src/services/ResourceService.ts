import { useState } from 'react';
import { PORT_SERVER } from '.';
import { useGetCurrentUser } from '../helpers/hooks/useGetCurrentUser';
import { Resource } from '../models/Resource';

interface createResourceRequestProps {
  resource: Resource,
}

const ResourceService = () => {
  // const [token, setToken] = useState<string>('')
  // const getCurrentToken = useGetCurrentUser;

  // const handleToken = (tokenString: string) => {
  //   setToken(tokenString)
  // }

  // getCurrentToken({ onUpdateUser: handleToken });

  // return {
  //   createResource: async (
  //     { resource }: createResourceRequestProps
  //   ) => {
  //     const url = `${PORT_SERVER}/resource/${resource.inventoryId}`
  //     const body = { ...resource }
  
  //     console.log('url');
  //     console.log(url);
  //     console.log('body');
  //     console.log(body);
  //     console.log('token');
  //     console.log(token);
  //   }
  // }
}



export {
  ResourceService,
}
