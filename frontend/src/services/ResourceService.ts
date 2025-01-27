import { useState } from 'react';
import { PORT_SERVER } from '.';
import { useGetCurrentToken } from '../helpers/hooks/useGetCurrentToken';
import { Resource } from '../models/Resource';

interface createResourceRequestProps {
  resource: Resource,
}

const ResourceService = () => {
  const [token, setToken] = useState<string>('')
  const getCurrentToken = useGetCurrentToken;

  const handleToken = (tokenString: string) => {
    setToken(tokenString)
  }

  getCurrentToken({ onUpdateToken: handleToken });

  return {
    createResource: async (
      { resource }: createResourceRequestProps
    ) => {
      const url = `${PORT_SERVER}/resource/${resource.inventoryId}`
      const body = { ...resource }

      console.log('url');
      console.log(url);
      console.log('body');
      console.log(body);
      console.log('token');
      console.log(token);
    }
  }
  
}



export {
  ResourceService,
}
