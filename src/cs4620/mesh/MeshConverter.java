package cs4620.mesh;

import java.util.ArrayList;

import egl.NativeMem;
import egl.math.Vector3;
import egl.math.Vector3i;

/**
 * Performs Normals Reconstruction Upon A Mesh Of Positions
 * @author Cristian
 *
 */
public class MeshConverter {
	/**
	 * Reconstruct A Mesh's Normals So That It Appears To Have Sharp Creases
	 * @param positions List Of Positions
	 * @param tris List Of Triangles (A Group Of 3 Values That Index Into The Positions List)
	 * @return A Mesh With Normals That Lie Normal To Faces
	 */
	public static MeshData convertToFaceNormals(ArrayList<Vector3> positions, ArrayList<Vector3i> tris) {
		MeshData data = new MeshData();

		// Notice
		System.out.println("This Feature Has Been Removed For The Sake Of Assignment Consistency");
		System.out.println("This Feature Will Be Added In A Later Assignment");
		
		// Please Do Not Fill In This Function With Code
		
		// After You Turn In Your Assignment, Chuck Norris Will
		// Substitute This Function With His Fiery Will Of Steel
		
		// TODO#A1 SOLUTION START
				
		// #SOLUTION END

		return data;
	}
	/**
	 * Reconstruct A Mesh's Normals So That It Appears To Be Smooth
	 * @param positions List Of Positions
	 * @param tris List Of Triangles (A Group Of 3 Values That Index Into The Positions List)
	 * @return A Mesh With Normals That Extrude From Vertices
	 */
	public static MeshData convertToVertexNormals(ArrayList<Vector3> positions, ArrayList<Vector3i> tris) {
		MeshData data = new MeshData();

		System.out.println(positions.size());
		System.out.println(tris.size());
		data.vertexCount=positions.size();
		data.indexCount=tris.size()*3;
		
		data.positions = NativeMem.createFloatBuffer(data.vertexCount * 3);
		data.normals = NativeMem.createFloatBuffer(data.vertexCount * 3);
		data.indices = NativeMem.createIntBuffer(data.indexCount);
		
		for(int i=0;i<positions.size();i++){
			data.positions.put(positions.get(i).x);
			data.positions.put(positions.get(i).y);
			data.positions.put(positions.get(i).z);
		}
		
		for(int i=0;i<tris.size();i++){
			data.indices.put(tris.get(i).x);
			data.indices.put(tris.get(i).y);
			data.indices.put(tris.get(i).z);
		}
		
		ArrayList<Vector3> normalarray = new ArrayList<Vector3>();
		for(int i=0;i<positions.size();++i){
			Vector3 normalvector=new Vector3();
			normalvector.setZero();
			normalarray.add(normalvector);
		}

		for(int i=0;i<tris.size();++i){
			Vector3 X=new Vector3(positions.get(tris.get(i).x));
			Vector3 Y=new Vector3(positions.get(tris.get(i).y));
			Vector3 Z=new Vector3(positions.get(tris.get(i).z));
			
			
			Vector3 XY=new Vector3(Y.sub(X));
			Vector3 XZ=new Vector3(Z.sub(X));
			
			Vector3 normal=new Vector3(XY.cross(XZ));
			
			//System.out.println(normal.x);
			Vector3 X1=normalarray.get(tris.get(i).x);
			Vector3 Y1=normalarray.get(tris.get(i).y);
			Vector3 Z1=normalarray.get(tris.get(i).z);
			
			X1.add(normal);
			Y1.add(normal);
			Z1.add(normal);
		}
		
		for(int i=0;i<positions.size();++i){
			Vector3 normalvector=new Vector3();
			normalvector=normalarray.get(i);
			normalvector.normalize();
			data.normals.put(normalvector.x);
			data.normals.put(normalvector.y);
			data.normals.put(normalvector.z);
		}
		
		// #SOLUTION END
		
		return data;
	}
}
